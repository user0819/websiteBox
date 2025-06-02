<template>
  <ElCard class="search-card">
    <ElForm :inline="true" label-width="60px" class="search-form">
      <div class="search-row">
        <div class="search-fields">
          <ElFormItem label="目录">
            <ElSelect v-model="query.categoryId" clearable style="width: 180px">
              <ElOption
                v-for="item in categoryData"
                :key="item.id"
                :label="item.name"
                :value="item.id!"
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
          <ElButton type="primary" @click="$emit('search')">查询</ElButton>
          <ElButton @click="$emit('reset')">重置</ElButton>
        </div>
      </div>
    </ElForm>
  </ElCard>
</template>

<script lang="ts" setup>
import {ElButton, ElCard, ElForm, ElFormItem, ElInput, ElOption, ElSelect,} from 'element-plus';

import type {CategoryInfo} from '#/api';

const {query, categoryData} = defineProps<{
  query: {
    categoryId?: number;
    name: string;
    url: string;
  };
  categoryData: CategoryInfo[];
}>();

const emit = defineEmits(['search', 'reset']);
</script>

<style scoped>
.search-card {
  margin-bottom: 16px;
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
