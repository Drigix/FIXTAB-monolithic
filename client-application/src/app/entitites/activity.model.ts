import { ActivityType } from "./activity-type.model";
import { Employee } from "./employee-model";
import { Result } from "./result.model";

interface IActivity {
  activityId?: number;
  sequenceNumber?: number;
  description?: string;
  cancelled?: boolean;
  status?: string;
  createDate?: string;
  statusUpateDate?: string;
  activityType?: ActivityType;
  employee?: Employee;
  result?: Result
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
  public status?: string,
  public createDate?: string,
  public statusUpateDate?: string,
  public activityType?: ActivityType,
  public employee?: Employee,
  public result?: Result
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
