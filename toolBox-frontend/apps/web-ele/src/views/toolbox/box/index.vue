<template>
  <Page>
    <ElCard>
      <div class="toolbox-container">
        <el-tabs v-model="activeTab" type="card" >
          <el-tab-pane
            v-for="category in categories"
            :key="category.id"
            :label="category.name"
            :name="String(category.id)"
          >
            <div class="website-row">
              <div
                v-for="detail in detailsByCategory[category.id]"
                :key="detail.id"
                class="website-card"
                @click="openWebsite(detail.url)"
              >
                <h3>{{ detail.name }}</h3>
                <a :href="detail.url" target="_blank">{{ detail.url }}</a>
                <p>{{ detail.description }}</p>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </ElCard>
  </Page>
</template>

<script setup lang="ts">
import {ElCard, ElTabPane, ElTabs} from 'element-plus';
import {Page} from '@vben/common-ui';
import {onMounted, reactive, ref} from 'vue';
import {getCategoryApi, getDetailsByCategory} from './services/boxService';
import type {CategoryInfo} from '#/views/toolbox/category/types/category';
import type {WebsiteInfo} from '#/views/toolbox/website/types/website';

const categories = ref<CategoryInfo[]>([]);
const detailsByCategory = reactive<Record<number, WebsiteInfo[]>>({});
const activeTab = ref<string>('');

onMounted(async () => {
  const res = await getCategoryApi();
  categories.value = res;

  if (res.length > 0) {
    activeTab.value = String(res[0]!.id);
  }

  for (const category of res) {
    detailsByCategory[category.id] = await getDetailsByCategory(category.id);
  }
});

function openWebsite(url: string) {
  window.open(url, '_blank');
}
</script>

<style scoped>
.toolbox-container {
  min-height: 100vh;
}

.website-row {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 24px;
  padding: 0 0 20px 0;
}

.website-card {
  border-radius: 10px;
  padding: 16px;
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.05);
  border: 1px solid #dce5ef;
  text-align: center; /* 添加文本居中 */
}

.website-card:hover {
  transform: translateY(3px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.website-card h3 {
  font-size: 1.1rem;
  margin-bottom: 8px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.website-card a {
  color: #1a73e8;
  font-size: 0.85rem;
  display: block;
  margin-bottom: 6px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.website-card p {
  font-size: 0.85rem;
  line-height: 1.4;

  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

:deep(.el-tabs--card>.el-tabs__header .el-tabs__nav) {
  border: none;
}


:deep(.el-tabs--card>.el-tabs__header .el-tabs__item) {
  border: none;
}


</style>
