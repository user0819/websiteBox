import { reactive, ref } from 'vue';
import { websiteService } from '../services/websiteService';
import { ElMessage, ElMessageBox } from 'element-plus';

export const useWebsiteForm = (fetchWebsite: () => Promise<void>) => {
  const dialogVisible = ref(false);
  const isEdit = ref(false);
  const currentItem = reactive({
    id: undefined,
    categoryId: undefined,
    name: '',
    url: '',
    description: '',
    sort: 0,
  });

  const openDialog = (item?: any) => {
    if (item) {
      Object.assign(currentItem, item);
      isEdit.value = true;
    } else {
      Object.assign(currentItem, {
        id: undefined,
        categoryId: undefined,
        name: '',
        url: '',
        description: '',
        sort: 0,
      });
      isEdit.value = false;
    }
    dialogVisible.value = true;
  };

  const submitForm = async () => {
    if (isEdit.value && currentItem.id) {
      await websiteService.updateWebsite(currentItem);
      ElMessage.success('修改成功');
    } else {
      await websiteService.addWebsite(currentItem);
      ElMessage.success('新增成功');
    }
    dialogVisible.value = false;
    await fetchWebsite();
  };

  const deleteItem = (id?: number) => {
    if (!id) return;
    ElMessageBox.confirm('确定要删除该网页吗？', '提示', {
      type: 'warning',
    })
      .then(async () => {
        await websiteService.deleteWebsite(id);
        ElMessage.success('删除成功');
        await fetchWebsite();
      })
      .catch(() => {});
  };

  return {
    dialogVisible,
    isEdit,
    currentItem,
    openDialog,
    submitForm,
    deleteItem,
  };
};
