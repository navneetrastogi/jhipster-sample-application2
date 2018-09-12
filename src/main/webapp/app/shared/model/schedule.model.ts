import { Moment } from 'moment';

export interface ISchedule {
  id?: string;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
}

export const defaultValue: Readonly<ISchedule> = {};
