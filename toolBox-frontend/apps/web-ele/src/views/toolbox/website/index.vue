<template>
  <Page>
    <SearchForm
      :query="query"
      :category-data="categoryData"
      @search="handleSearch"
      @reset="resetSearch"
    />

    <WebsiteTable
      :table-data="tableData"
      :total="total"
      :query="query"
      :category-data="categoryData"
      @add="openDialog"
      @edit="openDialog"
      @delete="deleteItem"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
    />

    <WebsiteDialog
      v-model:dialog-visible="dialogVisible"
      :is-edit="isEdit"
      :current-item="currentItem"
      :category-data="categoryData"
      @submit="submitForm"
    />
  </Page>
</template>

<script lang="ts" setup>
import {Page} from '@vben/common-ui';
import SearchForm from './components/SearchForm.vue';
import WebsiteTable from './components/WebsiteTable.vue';
import WebsiteDialog from './components/WebsiteDialog.vue';

import { onMounted } from 'vue';
import { useWebsiteList } from './composables/useWebsiteList';
import { useWebsiteForm } from './composables/useWebsiteForm';

// 列表逻辑
const { tableData, categoryData, total, query, fetchWebsite,fetchCategory} =
  useWebsiteList();

// 表单逻辑
const { dialogVisible, isEdit, currentItem, openDialog, submitForm, deleteItem } =
  useWebsiteForm(fetchWebsite);

// 查询与重置
const handleSearch = async () => {
  await fetchWebsite();
};

const resetSearch = () => {
  query.categoryId = undefined;
  query.name = '';
  query.url = '';
  fetchWebsite();
};


const handleSizeChange = async (newSize: number) => {
  query.pageSize = newSize;
  await fetchWebsite();
};

const handleCurrentChange = async (newPage: number) => {
  query.pageNum = newPage;
  await fetchWebsite();
};

onMounted(fetchWebsite);
onMounted(fetchCategory);

</script>
