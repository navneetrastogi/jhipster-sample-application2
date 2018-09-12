import { Moment } from 'moment';

export const enum Relation {
  FATHER = 'FATHER',
  MOTHER = 'MOTHER',
  GUARDIAN = 'GUARDIAN'
}

export interface IParent {
  id?: string;
  name?: string;
  createdOn?: Moment;
  phoneNumber?: string;
  relation?: Relation;
  studentName?: string;
  isAccountActive?: boolean;
  email?: string;
}

export const defaultValue: Readonly<IParent> = {
  isAccountActive: false
};
