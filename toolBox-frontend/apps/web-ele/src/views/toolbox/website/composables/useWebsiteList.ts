import {reactive, ref} from 'vue';
import { websiteService } from '../services/websiteService';

export const useWebsiteList = () => {
  const tableData = ref<any[]>([]);
  const categoryData = ref<any[]>([]);
  const total = ref(0);
  const query = reactive({
    categoryId: undefined as number | undefined,
    name: '',
    url: '',
    pageNum: 1,
    pageSize: 10,
  });

  const fetchWebsite = async () => {
    const res = await websiteService.getWebsite(query);
    tableData.value = res.list;
    total.value = res.total;
  };

  const fetchCategory = async () => {
    const res = await websiteService.getCategory();
    if (Array.isArray(res)) {
      categoryData.value = res;
    }
  };

  const handleSizeChange = async (newSize: number) => {
    query.pageSize = newSize;
    await fetchWebsite();
  };

  const handleCurrentChange = async (newPage: number) => {
    query.pageNum = newPage;
    await fetchWebsite();
  };


  return {
    tableData,
    categoryData,
    total,
    query,
    fetchWebsite,
    fetchCategory,
    handleSizeChange,
    handleCurrentChange,
  };
};
