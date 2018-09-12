import { Moment } from 'moment';

export interface IActivity {
  id?: string;
  title?: string;
  description?: string;
  activityImageUrl?: string;
  activityDate?: Moment;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
  studentIds?: string;
}

export const defaultValue: Readonly<IActivity> = {};
