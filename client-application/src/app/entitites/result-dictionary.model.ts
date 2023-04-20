interface IResultDictionary {
  resultId?: number;
  name?: string;
}

export class ResultDictionary implements IResultDictionary {
  constructor(
    public resultId?: number,
    public name?: string
  ) {}

  static resultDictionaryList: ResultDictionary[] = [
    {
      resultId: 1,
      name: 'OPEN'
    },
    {
      resultId: 2,
      name: 'PROGRESS'
    },
    {
      resultId: 3,
      name: 'CANCEL'
    },
    {
      resultId: 4,
      name: 'FINISH'
    },
  ];

  static statusOpen = this.resultDictionaryList[0];
  static statusProgress = this.resultDictionaryList[1];
  static statusCancel = this.resultDictionaryList[2];
  static statusFinish = this.resultDictionaryList[3];
}
