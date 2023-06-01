interface IStatusDictionary {
  statusId?: number;
  name?: string;
}

export class StatusDictionary implements IStatusDictionary {
  constructor(
    public statusId?: number,
    public name?: string,
    public plName?: string
  ) {}

  static statusDictionaryList: StatusDictionary[] = [
    {
      statusId: 1,
      name: 'OPEN',
      plName: 'OTWARTY'
    },
    {
      statusId: 2,
      name: 'PROGRESS',
      plName: 'W TRAKCIE'
    },
    {
      statusId: 3,
      name: 'CANCEL',
      plName: 'ODWOŁANY'
    },
    {
      statusId: 4,
      name: 'FINISH',
      plName: 'ZAKOŃCZONY'
    },
  ];

  static statusOpen = this.statusDictionaryList[0];
  static statusProgress = this.statusDictionaryList[1];
  static statusCancel = this.statusDictionaryList[2];
  static statusFinish = this.statusDictionaryList[3];
}
