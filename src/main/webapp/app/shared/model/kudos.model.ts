import { Moment } from 'moment';

export interface IKudos {
  id?: string;
  title?: string;
  description?: string;
  imageUrl?: string;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
}

export const defaultValue: Readonly<IKudos> = {};
