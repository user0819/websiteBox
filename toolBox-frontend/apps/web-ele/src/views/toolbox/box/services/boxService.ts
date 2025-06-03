import type {CategoryInfo} from '#/views/toolbox/category/types/category';
import type {WebsiteInfo} from '#/views/toolbox/website/types/website';
import {requestClient} from "#/api/request";


/**
 * 查询
 */
export async function getCategoryApi() {
  return requestClient.get<CategoryInfo[]>('/category');
}


export async function getDetailsByCategory(categoryId: number | undefined) {
  return requestClient.get<WebsiteInfo[]>(`/details/category/${categoryId}`);
}
