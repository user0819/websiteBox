import type { WebsiteInfo } from '#/api';
import {
  addWebsiteApi,
  deleteWebsiteApi,
  getCategoryApi,
  getWebsiteApi,
  updateWebsiteApi,
} from '#/api';

export const websiteService = {
  getWebsite: (params: any) => getWebsiteApi(params),
  addWebsite: (data: WebsiteInfo) => addWebsiteApi(data),
  updateWebsite: (data: WebsiteInfo) => updateWebsiteApi(data),
  deleteWebsite: (id?: number) => deleteWebsiteApi(id),
  getCategory: () => getCategoryApi(),
};
