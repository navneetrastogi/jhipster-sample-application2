import { Moment } from 'moment';

export interface IIncident {
  id?: string;
  natureOfIncident?: string;
  firstAidProvided?: boolean;
  firstAidNotes?: string;
  date?: Moment;
  createdOn?: Moment;
  isAdminOnly?: boolean;
  notes?: string;
  role?: string;
}

export const defaultValue: Readonly<IIncident> = {
  firstAidProvided: false,
  isAdminOnly: false
};
