import { Moment } from 'moment';

export interface IOrganization {
  id?: string;
  name?: string;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
}

export const defaultValue: Readonly<IOrganization> = {};
