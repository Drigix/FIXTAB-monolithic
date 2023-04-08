interface IArchive {
  archiveId?: number;
  tableName?: string;
}

export class Archive implements IArchive {
  constructor(
    public archiveId?: number,
    public tableName?: string
  ) {}
}
