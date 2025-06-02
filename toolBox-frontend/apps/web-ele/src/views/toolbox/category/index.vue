<script lang="ts" setup>
import type { CategoryInfo } from './types/category';
import {
  addCategoryApi,
  deleteCategoryApi,
  getCategoryApi,
  updateCategoryApi,
} from './services/categoryService';

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
  ElTable,
} from 'element-plus';


const tableData = ref<CategoryInfo[]>([]);
const dialogVisible = ref(false);
const isEdit = ref(false);
const currentItem = reactive<CategoryInfo>({
  id: undefined,
  name: '',
  description: '',
  sort: 0,
});

const fetchCategories = async () => {
  const res = await getCategoryApi();
  if (Array.isArray(res)) {
    tableData.value = res;
  }
};

const openDialog = (item?: CategoryInfo) => {
  if (item) {
    Object.assign(currentItem, item);
    isEdit.value = true;
  } else {
    Object.assign(currentItem, { id: undefined, name: '', description: '', sort: 0 });
    isEdit.value = false;
  }
  dialogVisible.value = true;
};

const submitForm = async () => {
  if (isEdit.value && currentItem.id) {
    await updateCategoryApi(currentItem);
    ElMessage.success('修改成功');
  } else {
    await addCategoryApi(currentItem);
    ElMessage.success('新增成功');
  }
  dialogVisible.value = false;
  await fetchCategories();
};

const deleteItem = (id?: number) => {
  if (!id) return;
  ElMessageBox.confirm('确定要删除该目录吗？', '提示', {
    type: 'warning',
  })
    .then(async () => {
      await deleteCategoryApi(id);
      ElMessage.success('删除成功');
      await fetchCategories();
    })
    .catch(() => {});
};

onMounted(fetchCategories);
</script>

<template>
  <Page>
    <ElCard>
      <template #header>
        <div class="card-header-wrapper">
          <span>目录管理</span>
          <ElButton type="primary" @click="openDialog()">新增</ElButton>
        </div>
      </template>

      <ElTable :data="tableData" stripe border highlight-current-row>
        <ElTable.TableColumn type="index" width="50" />
        <ElTable.TableColumn label="目录" prop="name" />
        <ElTable.TableColumn label="序号" prop="sort" />
        <ElTable.TableColumn label="描述" prop="description" />
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
      :title="isEdit ? '编辑目录' : '新增目录'"
      width="500px"
    >
      <ElForm label-width="80px">
        <ElFormItem label="名称">
          <ElInput v-model="currentItem.name" />
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
.card-header-wrapper {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>
