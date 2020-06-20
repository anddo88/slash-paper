export class LocaleWord {

  readonly NOTIFICATION_TITLE = 'NOTIFICATION_TITLE';

  readonly THEME_DEFAULT = 'THEME_DEFAULT';
  readonly THEME_COSMIC = 'THEME_COSMIC';
  readonly THEME_CORPORATE = 'THEME_CORPORATE';
  readonly THEME_DARK = 'THEME_DARK';

  static instance = new LocaleWord();

  private constructor() {
  }

  static getInstance() {
    return this.instance;
  }
}
