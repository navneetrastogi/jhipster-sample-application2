import { Moment } from 'moment';

export interface IIllnessRecord {
  id?: string;
  createdOn?: Moment;
  name?: string;
  description?: string;
  isCured?: boolean;
}

export const defaultValue: Readonly<IIllnessRecord> = {
  isCured: false
};
