import { requestClient } from '#/api/request';

interface WebsiteInfo {
  id?: number;
  categoryId?: number;
  name: string;
  url: string;
  description: string;
  sort: number;
}

interface WebsiteInfoPage {
  total: number;
  list: WebsiteInfo[];
}

export type { WebsiteInfo };
export type { WebsiteInfoPage };

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
