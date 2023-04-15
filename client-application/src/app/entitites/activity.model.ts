interface IActivity {
  id?: number;
  lp?: number;
  name?: string;
}

export class Activity implements IActivity {
  constructor(
  public id?: number,
  public lp?: number,
  public name?: string
  ) {}
}
