import { Moment } from 'moment';

export const enum ScheduleItemType {
  EVENT = 'EVENT',
  ACTIVITY = 'ACTIVITY'
}

export interface IScheduleItem {
  id?: string;
  isDone?: boolean;
  itemType?: ScheduleItemType;
  title?: string;
  description?: string;
  scheduledDate?: Moment;
  startTime?: Moment;
  endtTime?: Moment;
}

export const defaultValue: Readonly<IScheduleItem> = {
  isDone: false
};
