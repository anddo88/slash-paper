export class ErrorName {
  readonly UNEXPECTED_ERROR = 'UNEXPECTED_ERROR';
  readonly EXPECTED_ERROR = 'EXPECTED_ERROR';
  static instance = new ErrorName();

  private constructor() {
  }

  static getInstance() {
    return this.instance;
  }
}
