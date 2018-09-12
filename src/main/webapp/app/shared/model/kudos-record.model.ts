import { Moment } from 'moment';

export interface IKudosRecord {
  id?: string;
  createdOn?: Moment;
  notes?: string;
}

export const defaultValue: Readonly<IKudosRecord> = {};
