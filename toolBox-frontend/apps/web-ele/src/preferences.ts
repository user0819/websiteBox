import { defineOverridesPreferences } from '@vben/preferences';
import favicon from '#/assets/favicon.svg';
/**
 * @description 项目配置文件
 * 只需要覆盖项目中的一部分配置，不需要的配置不用覆盖，会自动使用默认配置
 * !!! 更改配置后请清空缓存，否则可能不生效
 */
export const overridesPreferences = defineOverridesPreferences({
  app: {
    enablePreferences: false,
    authPageLayout: 'panel-center',
    layout: 'header-nav',
    dynamicTitle: false,
    enableCheckUpdates: false,
  },
  header: {
    menuAlign: 'center',
  },
  tabbar: {
    enable: false,
  },
  theme: {
    mode: 'light',
  },
  widget: {
    fullscreen: false,
    languageToggle: false,
    lockScreen: false,
    notification: false,
    refresh: false,
    sidebarToggle: false,
    themeToggle: true,
    globalSearch: true
  },
  logo: {
     enable: true,
     source: favicon
  },
  shortcutKeys: {
    enable: false,
    globalLockScreen: false,
    globalSearch: false
  },
  sidebar: {
    collapsedButton: false,
    fixedButton: false
  },
});
