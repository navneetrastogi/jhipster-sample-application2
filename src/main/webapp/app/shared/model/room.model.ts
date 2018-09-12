import { Moment } from 'moment';

export interface IRoom {
  id?: string;
  name?: string;
  capacity?: number;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
}

export const defaultValue: Readonly<IRoom> = {};
