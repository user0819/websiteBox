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

