import {ElementRef, Injectable} from '@angular/core';
import {ErrorName} from '../../common/constant';
import {ApiError} from '../../common/interfaces';

@Injectable({providedIn: 'root'})
export class ErrorHandlingUtil {
  private errorsParentElement: ElementRef<HTMLDivElement>;
  private errors: { [idOrName: string]: string } = {};

  constructor() {
  }

  messagesOfCodes(codesOrNames: string[]): string[] {
    if (!codesOrNames || !Array.isArray(codesOrNames)) {
      throw new TypeError('Error code or name is not found !');
    }

    const messages: string[] = [];

    // or use fillMessagesFromElements method
    this.fillMessagesFromElements(messages, codesOrNames);

    return messages;
  }

  private fillFromArray(messages: string[], codesOrNames:string[])
  {
    const firstCodeOrName = codesOrNames[0];
    if (Number(firstCodeOrName.substr(1)) && !isNaN(Number(firstCodeOrName.substr(1)))) {
      codesOrNames.forEach(code => {
        messages.push(this.errors[code]);
      });
    } else {
      codesOrNames.forEach(name => {
        messages.push(this.errors[name]);
      });
    }
  }

  private fillMessagesFromElements(messages: string[], codesOrNames:string[])
  {
    const firstCodeOrName = codesOrNames[0];
    if (Number(firstCodeOrName.substr(1)) && !isNaN(Number(firstCodeOrName.substr(1)))) {
      codesOrNames.forEach(code => {
        messages.push(this.errorsParentElement.nativeElement.querySelector<HTMLInputElement>(`#${code}`).value);
      });
    } else {
      codesOrNames.forEach(name => {
        messages.push(this.errorsParentElement.nativeElement.querySelector<HTMLInputElement>(`[name=${name}]`).value);
      });
    }
  }


  parentElementBundle(errorsParentElement: ElementRef<HTMLDivElement>): void {
    this.errorsParentElement = errorsParentElement;
    const elementsSize = errorsParentElement.nativeElement.children.length;
    const children = errorsParentElement.nativeElement.children;
    for (let i = 0; i < elementsSize; i++) {
      this.errors[(<HTMLInputElement> children.item(i)).id] = (<HTMLInputElement> children.item(i)).value;
      this.errors[(<HTMLInputElement> children.item(i)).name] = (<HTMLInputElement> children.item(i)).value;
      if (!ErrorName.getInstance().hasOwnProperty((<HTMLInputElement> children.item(i)).name)) {
        throw new TypeError('Error code or name is not found !');
      }
    }
  }

  codesOf(apiError: ApiError): string[] {
    return Object.keys(apiError.errorCodes);
  }
}
