interface IStatus {
  statusId?: number;
  name?: string;
}

export class Status implements IStatus {
  constructor(
  public statusId?: number,
  public name?: string
  ) {}
}
