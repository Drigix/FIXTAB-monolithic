interface IResultDictionary {
  resultId?: number;
  name?: string;
}

export class ResultDictionary implements IResultDictionary {
  constructor(
    public resultId?: number,
    public name?: string,
    public plName?: string
  ) {}

  static resultDictionaryList: ResultDictionary[] = [
    {
      resultId: 1,
      name: 'OPEN',
      plName: 'OTWARTY'
    },
    {
      resultId: 2,
      name: 'PROGRESS',
      plName: 'W TRAKCIE'
    },
    {
      resultId: 3,
      name: 'CANCEL',
      plName: 'ODWOŁANY'
    },
    {
      resultId: 4,
      name: 'FINISH',
      plName: 'ZAKOŃCZONY'
    },
  ];

  static statusOpen = this.resultDictionaryList[0];
  static statusProgress = this.resultDictionaryList[1];
  static statusCancel = this.resultDictionaryList[2];
  static statusFinish = this.resultDictionaryList[3];
}
