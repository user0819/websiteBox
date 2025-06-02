import type { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [

  {
    name: 'category',
    path: '/category',
    component: () => import('#/views/toolbox/category/index.vue'),
    meta: {
      icon: 'lucide:layers',
      title: 'category',
      order: 1,
    },
  },
  {
    name: 'website',
    path: '/website',
    component: () => import('#/views/toolbox/website/index.vue'),
    meta: {
      icon: 'lucide:link',
      title: 'website',
      order: 2,
    },
  },

  {
    name: 'ToolBox1',
    path: '/toolbox',
    component: () => import('#/views/toolbox/toolbox/index.vue'),
    meta: {
      icon: 'lucide:book-open',
      title: 'ToolBox',
      order: 3,
    },
  },

];

export default routes;
