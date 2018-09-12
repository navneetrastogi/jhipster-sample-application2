import { Moment } from 'moment';

export interface IHoliday {
  id?: string;
  date?: Moment;
  title?: string;
  description?: string;
}

export const defaultValue: Readonly<IHoliday> = {};
