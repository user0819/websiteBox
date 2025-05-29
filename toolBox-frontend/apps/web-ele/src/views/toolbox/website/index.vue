<script lang="ts" setup>
import type { CategoryInfo, WebsiteInfo } from '#/api';

import { onMounted, reactive, ref } from 'vue';

import { Page } from '@vben/common-ui';

import {
  ElButton,
  ElCard,
  ElDialog,
  ElForm,
  ElFormItem,
  ElInput,
  ElInputNumber,
  ElMessage,
  ElMessageBox,
  ElOption,
  ElSelect,
  ElTable,
} from 'element-plus';

import {
  addWebsiteApi,
  deleteWebsiteApi,
  getCategoryApi,
  getWebsiteApi,
  updateWebsiteApi,
} from '#/api';

const tableData = ref<WebsiteInfo[]>([]);
const categoryData = ref<CategoryInfo[]>([]);
const dialogVisible = ref(false);
const isEdit = ref(false);
const currentItem = reactive<WebsiteInfo>({
  id: undefined,
  categoryId: undefined,
  name: '',
  url: '',
  description: '',
  sort: 0,
});

const fetchWebsite = async () => {
  const res = await getWebsiteApi(query);
  if (Array.isArray(res)) {
    tableData.value = res;
  }
};

const fetchCategory = async () => {
  const res = await getCategoryApi();
  if (Array.isArray(res)) {
    categoryData.value = res;
  }
};

const openDialog = (item?: WebsiteInfo) => {
  if (item) {
    Object.assign(currentItem, item);
    isEdit.value = true;
  } else {
    Object.assign(currentItem, {
      id: undefined,
      categoryId: undefined,
      name: '',
      url: '',
      description: '',
      sort: 0,
    });
    isEdit.value = false;
  }
  dialogVisible.value = true;
};

const submitForm = async () => {
  if (isEdit.value && currentItem.id) {
    await updateWebsiteApi(currentItem);
    ElMessage.success('修改成功');
  } else {
    await addWebsiteApi(currentItem);
    ElMessage.success('新增成功');
  }
  dialogVisible.value = false;
  await fetchWebsite();
};

const deleteItem = (id?: number) => {
  if (!id) return;
  ElMessageBox.confirm('确定要删除该网页吗？', '提示', {
    type: 'warning',
  })
    .then(async () => {
      await deleteWebsiteApi(id);
      ElMessage.success('删除成功');
      await fetchWebsite();
    })
    .catch(() => {});
};

const getCategoryName = (categoryId?: number): string => {
  const category = categoryData.value.find((item) => item.id === categoryId);
  return category ? category.name : '';
};

const query = reactive({
  categoryId: undefined as number | undefined,
  name: '',
  url: '',
});

const handleSearch = async () => {
  const res = await getWebsiteApi(query); // 假设API支持传参过滤
  if (Array.isArray(res)) {
    tableData.value = res;
  }
};

const resetSearch = () => {
  query.categoryId = undefined;
  query.name = '';
  query.url = '';
  fetchWebsite(); // 重新加载全部数据
};

onMounted(fetchWebsite);
onMounted(fetchCategory);
</script>

<template>
  <Page>
    <!-- 查询区域 -->
    <ElCard class="search-card">
      <ElForm :inline="true" label-width="60px" class="search-form">
        <div class="search-row">
          <div class="search-fields">
            <ElFormItem label="目录">
              <ElSelect
                v-model="query.categoryId"
                clearable
                style="width: 180px"
              >
                <ElOption
                  v-for="item in categoryData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </ElSelect>
            </ElFormItem>
            <ElFormItem label="名称">
              <ElInput
                v-model="query.name"
                clearable
                placeholder="请输入名称"
                style="width: 180px"
              />
            </ElFormItem>
            <ElFormItem label="链接">
              <ElInput
                v-model="query.url"
                clearable
                placeholder="请输入链接"
                style="width: 180px"
              />
            </ElFormItem>
          </div>

          <div class="search-actions">
            <ElButton type="primary" @click="handleSearch">查询</ElButton>
            <ElButton @click="resetSearch">重置</ElButton>
          </div>
        </div>
      </ElForm>
    </ElCard>

    <!-- 表格区域 -->
    <ElCard class="table-card">
      <template #header>
        <div class="card-header-wrapper">
          <span>网页管理</span>
          <ElButton type="primary" @click="openDialog()">新增</ElButton>
        </div>
      </template>

      <ElTable :data="tableData" stripe border highlight-current-row>
        <ElTable.TableColumn type="index" width="50" />
        <ElTable.TableColumn label="目录" prop="categoryId">
          <template #default="scope">
            {{ getCategoryName(scope.row.categoryId) }}
          </template>
        </ElTable.TableColumn>
        <ElTable.TableColumn label="名称" prop="name" />
        <ElTable.TableColumn label="链接" prop="url" />
        <ElTable.TableColumn label="描述" prop="description" />
        <ElTable.TableColumn label="序号" prop="sort" />
        <ElTable.TableColumn fixed="right" width="250" label="操作">
          <template #default="scope">
            <ElButton
              type="primary"
              size="small"
              @click="openDialog(scope.row)"
            >
              编辑
            </ElButton>
            <ElButton
              type="danger"
              size="small"
              @click="deleteItem(scope.row.id)"
            >
              删除
            </ElButton>
          </template>
        </ElTable.TableColumn>
      </ElTable>
    </ElCard>

    <!-- 弹窗表单 -->
    <ElDialog
      v-model="dialogVisible"
      :title="isEdit ? '编辑网页' : '新增网页'"
      width="500px"
    >
      <ElForm label-width="80px">
        <ElFormItem label="目录">
          <ElSelect v-model="currentItem.categoryId">
            <ElOption
              v-for="item in categoryData"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </ElSelect>
        </ElFormItem>
        <ElFormItem label="名称">
          <ElInput v-model="currentItem.name" />
        </ElFormItem>
        <ElFormItem label="链接">
          <ElInput v-model="currentItem.url" />
        </ElFormItem>
        <ElFormItem label="描述">
          <ElInput v-model="currentItem.description" />
        </ElFormItem>
        <ElFormItem label="序号">
          <ElInputNumber v-model="currentItem.sort" :min="0" />
        </ElFormItem>
      </ElForm>

      <template #footer>
        <ElButton @click="dialogVisible = false">取消</ElButton>
        <ElButton type="primary" @click="submitForm">提交</ElButton>
      </template>
    </ElDialog>
  </Page>
</template>

<style scoped>
.search-card {
  margin-bottom: 16px;
}

.table-card {
  margin-top: 16px;
}

.card-header-wrapper {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.search-form .search-row {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}

.search-form .search-fields {
  display: flex;
  gap: 16px;
  margin-bottom: 10px;
}

.search-form .search-actions {
  display: flex;
  justify-content: flex-end;
  width: 100%;
  margin-top: 10px;
}
</style>
