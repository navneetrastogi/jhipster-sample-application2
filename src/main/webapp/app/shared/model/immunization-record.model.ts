import { Moment } from 'moment';

export interface IImmunizationRecord {
  id?: string;
  immunizationitemId?: number;
  createdOn?: Moment;
  vaccinationDoneOn?: Moment;
  vaccinationName?: string;
  isOnTime?: boolean;
  imageUrl?: string;
}

export const defaultValue: Readonly<IImmunizationRecord> = {
  isOnTime: false
};
