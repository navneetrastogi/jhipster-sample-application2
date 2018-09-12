import { Moment } from 'moment';

export interface IStudent {
  id?: string;
  name?: string;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
  profileImageURL?: string;
}

export const defaultValue: Readonly<IStudent> = {};
