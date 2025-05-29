import type { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    name: 'ToolBox1',
    path: '/toolbox',
    component: () => import('#/views/toolbox/toolbox/index.vue'),
    meta: {
      icon: 'lucide:copyright',
      title: 'ToolBox',
      order: -1,
    },
  },

  {
    name: 'category',
    path: '/category',
    component: () => import('#/views/toolbox/category/index.vue'),
    meta: {
      icon: 'lucide:copyright',
      title: 'category',
      order: -1,
    },
  },
  {
    name: 'website',
    path: '/website',
    component: () => import('#/views/toolbox/website/index.vue'),
    meta: {
      icon: 'lucide:copyright',
      title: 'website',
      order: -1,
    },
  },

];

export default routes;
