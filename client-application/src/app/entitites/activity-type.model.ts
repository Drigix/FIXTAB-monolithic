interface IActivityType {
  typeId?: number;
  typeName?: string;
}

export class ActivityType implements IActivityType {
  constructor(
  public typeId?: number,
  public typeName?: string
  ) {}
}
