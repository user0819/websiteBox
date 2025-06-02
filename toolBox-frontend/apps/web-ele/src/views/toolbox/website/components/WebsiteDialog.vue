<template>
  <ElDialog
    :modelValue="dialogVisible"
    @update:modelValue="dialogVisible = $event"
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
      <ElButton @click="emit('update:dialogVisible', false)">取消</ElButton>
      <ElButton type="primary" @click="$emit('submit')">提交</ElButton>
    </template>
  </ElDialog>
</template>

<script lang="ts" setup>
import {
  ElDialog,
  ElForm,
  ElFormItem,
  ElSelect,
  ElOption,
  ElInput,
  ElInputNumber,
  ElButton,
} from 'element-plus';

import type { WebsiteInfo } from '../types/website';


const { dialogVisible, isEdit, currentItem, categoryData }  = defineProps<{
  dialogVisible: boolean;
  isEdit: boolean;
  currentItem: WebsiteInfo;
  categoryData: any[];
}>();

const emit = defineEmits(['update:dialogVisible', 'submit']);
</script>
