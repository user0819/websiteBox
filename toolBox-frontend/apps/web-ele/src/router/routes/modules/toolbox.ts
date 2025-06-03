import type { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    name: 'ToolBox',
    path: '/toolbox',
    component: () => import('#/views/toolbox/box/index.vue'),
    meta: {
      icon: 'lucide:box',
      title: 'ToolBox',
      order: 0,
    },
  },

  {
    name: 'Category',
    path: '/category',
    component: () => import('#/views/toolbox/category/index.vue'),
    meta: {
      icon: 'lucide:layers',
      title: 'Category',
      order: 1,
    },
  },
  {
    name: 'Website',
    path: '/website',
    component: () => import('#/views/toolbox/website/index.vue'),
    meta: {
      icon: 'lucide:link',
      title: 'Website',
      order: 2,
    },
  },

];

export default routes;
