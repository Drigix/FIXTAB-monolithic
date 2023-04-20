interface IActivityType {
  typeId?: number;
  typeName?: string;
}

export class ActivityType implements IActivityType {
  constructor(
  public activityId?: number,
  public typeName?: string
  ) {}
}
