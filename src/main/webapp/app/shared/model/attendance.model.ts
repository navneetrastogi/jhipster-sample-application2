import { Moment } from 'moment';

export interface IAttendance {
  id?: string;
  checkin?: Moment;
  checkout?: Moment;
  datetime?: Moment;
  createdOn?: Moment;
}

export const defaultValue: Readonly<IAttendance> = {};
