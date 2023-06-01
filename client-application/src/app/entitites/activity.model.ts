import { ActivityType } from "./activity-type.model";
import { Employee } from "./employee-model";
import { StatusDictionary } from "./result-dictionary.model";
import { Status } from "./result.model";

interface IActivity {
  activityId?: number;
  sequenceNumber?: number;
  description?: string;
  cancelled?: boolean;
  result?: string;
  createDate?: string;
  statusUpateDate?: string;
  activityType?: ActivityType;
  manager?: Employee;
  employee?: Employee;
  status?: Status
}

interface IActivityRequest {
  sequenceNumber?: number;
  description?: string;
  activityType?: ActivityType;
  employeeId?: number;
}

export class Activity implements IActivity {
  constructor(
  public activityId?: number,
  public sequenceNumber?: number,
  public description?: string,
  public cancelled?: boolean,
  public result?: string,
  public createDate?: string,
  public statusUpateDate?: string,
  public activityType?: ActivityType,
  public manager?: Employee,
  public employee?: Employee,
  public status?: Status
  ) {}
}

export class ActivityRequest implements IActivityRequest {
  constructor(
  public sequenceNumber?: number,
  public description?: string,
  public activityType?: ActivityType,
  public employeeId?: number
  ) {}
}
