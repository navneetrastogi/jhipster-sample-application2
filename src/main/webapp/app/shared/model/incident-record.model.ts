import { Moment } from 'moment';

export interface IIncidentRecord {
  id?: string;
  natureOfIncident?: string;
  firstAidProvided?: boolean;
  firstAidNotes?: string;
  date?: Moment;
  createdOn?: Moment;
  isAdminOnly?: boolean;
  notes?: string;
  students?: string;
}

export const defaultValue: Readonly<IIncidentRecord> = {
  firstAidProvided: false,
  isAdminOnly: false
};
