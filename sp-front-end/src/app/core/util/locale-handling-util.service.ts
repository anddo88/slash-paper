import {ElementRef, Injectable, QueryList} from '@angular/core';
import {LocaleWord} from '../../common/constant/locale-word';

@Injectable({providedIn: 'root'})
export class LocaleHandlingUtil {
  private inputElements: QueryList<ElementRef<HTMLInputElement>>;
  private storedLocale: { [name: string]: string } = {};

  constructor() {
  }

  translationOf(localeName: string): string {
    return this.fillMessagesFromElements(localeName);
  }

  private fillFromArray(localeName: string): string {
    return this.storedLocale[localeName];
  }

  private fillMessagesFromElements(localeName: string): string {
    return this.inputElements.find((element) => {return element.nativeElement.id === localeName}).nativeElement.value;
  }


  parentElementBundle(inputElements: QueryList<ElementRef<HTMLInputElement>>): void {
    this.inputElements = inputElements;
    inputElements.forEach((element: ElementRef<HTMLInputElement>) => {
      this.storedLocale[element.nativeElement.id] = element.nativeElement.value;
    });
  }
}
