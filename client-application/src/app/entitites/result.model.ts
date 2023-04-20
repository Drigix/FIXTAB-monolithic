interface IResult {
  resultId?: number;
  result?: string;
}

export class Result implements IResult {
  constructor(
  public resultId?: number,
  public result?: string
  ) {}
}
