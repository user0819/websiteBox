import type {CategoryInfo} from '../types/category';
import {requestClient} from "#/api/request";


/**
 * 新增
 */
export async function addCategoryApi(data: CategoryInfo) {
  return requestClient.post<CategoryInfo>('/category', data);
}

/**
 * 删除
 */
export async function deleteCategoryApi(id: number) {
  return requestClient.delete<CategoryInfo>(`/category/${id}`);
}

/**
 * 修改
 */
export async function updateCategoryApi(data: CategoryInfo) {
  return requestClient.put<CategoryInfo>('/category', data);
}

/**
 * 查询
 */
export async function getCategoryApi() {
  return requestClient.get<CategoryInfo[]>('/category');
}
