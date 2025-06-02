export interface CategoryInfo {
  id: number;
  name: string;
}

export interface WebsiteInfo {
  id?: number;
  categoryId?: number;
  name: string;
  url: string;
  description: string;
  sort: number;
}
