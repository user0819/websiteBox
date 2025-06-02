<template>
  <ElCard class="table-card">
    <template #header>
      <div class="card-header-wrapper">
        <span>网页管理</span>
        <ElButton type="primary" @click="$emit('add')">新增</ElButton>
      </div>
    </template>

    <template #default>
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
            <ElButton type="primary" size="small" @click="$emit('edit', scope.row)">编辑</ElButton>
            <ElButton type="danger" size="small" @click="$emit('delete', scope.row.id)">删除</ElButton>
          </template>
        </ElTable.TableColumn>
      </ElTable>
    </template>

    <template #footer>
      <div class="pagination-container">
        <ElPagination
          v-model:current-page="query.pageNum"
          v-model:page-size="query.pageSize"
          :total="total"
          :page-sizes="[10, 20, 50, 100]"
          layout="prev, pager, next, sizes, total, jumper"
          @current-change="$emit('current-change')"
          @size-change="$emit('size-change')"
        />
      </div>
    </template>
  </ElCard>
</template>

<script lang="ts" setup>
import {
  ElCard,
  ElTable,
  ElButton,
  ElPagination,
} from 'element-plus';

import type { WebsiteInfo } from '#/api';

const props = defineProps<{
  tableData: WebsiteInfo[];
  total: number;
  query: {
    pageNum: number;
    pageSize: number;
  };
  categoryData: any[];
}>();

const emit = defineEmits([
  'add',
  'edit',
  'delete',
  'current-change',
  'size-change',
]);

const getCategoryName = (categoryId?: number): string => {
  const category = props.categoryData.find((item) => item.id === categoryId);
  return category ? category.name : '';
};
</script>

<style scoped>
.card-header-wrapper {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.table-card {
  margin-top: 16px;
}

.pagination-container {
  display: flex;
  justify-content: flex-end;
  width: 100%;
}
</style>
