import type { WebsiteInfo, WebsiteInfoPage} from '../types/website';
import { getCategoryApi } from '#/views/toolbox/category/services/categoryService';
import {requestClient} from "#/api/request";


/**
 * 新增
 */
export async function addWebsiteApi(data: WebsiteInfo) {
  return requestClient.post<WebsiteInfo>('/website', data);
}

/**
 * 删除
 */
export async function deleteWebsiteApi(id: number | undefined) {
  return requestClient.delete<WebsiteInfo>(`/website/${id}`);
}

/**
 * 修改
 */
export async function updateWebsiteApi(data: WebsiteInfo) {
  return requestClient.put<WebsiteInfo>('/website', data);
}

/**
 * 查询
 */
export async function getWebsiteApi(
  query: {
    categoryId?: number;
    name?: string;
    url?: string;
  } = {},
) {
  return requestClient.get<WebsiteInfoPage>('/website', {
    params: query,
  });
}


export const websiteService = {
  getWebsite: (params: any) => getWebsiteApi(params),
  addWebsite: (data: WebsiteInfo) => addWebsiteApi(data),
  updateWebsite: (data: WebsiteInfo) => updateWebsiteApi(data),
  deleteWebsite: (id?: number) => deleteWebsiteApi(id),
  getCategory: () => getCategoryApi(),
};
