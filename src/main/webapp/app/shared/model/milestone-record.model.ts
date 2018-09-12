import { Moment } from 'moment';

export interface IMilestoneRecord {
  id?: string;
  createdOn?: Moment;
  notes?: string;
}

export const defaultValue: Readonly<IMilestoneRecord> = {};
