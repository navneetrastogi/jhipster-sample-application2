import { Moment } from 'moment';

export interface ICenter {
  id?: string;
  name?: string;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
}

export const defaultValue: Readonly<ICenter> = {};
