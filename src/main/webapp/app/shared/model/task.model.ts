import { Moment } from 'moment';

export const enum ReminderType {
  HOURLY = 'HOURLY',
  DAILY = 'DAILY',
  WEEKLY = 'WEEKLY',
  MONTHLY = 'MONTHLY'
}

export interface ITask {
  id?: string;
  title?: string;
  notes?: string;
  startDate?: Moment;
  endDate?: Moment;
  reminderType?: ReminderType;
}

export const defaultValue: Readonly<ITask> = {};
