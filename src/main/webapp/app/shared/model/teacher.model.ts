import { Moment } from 'moment';

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE'
}

export interface ITeacher {
  id?: string;
  name?: string;
  contactNumber?: string;
  gender?: Gender;
  address?: string;
  createdOn?: Moment;
  joiningDate?: Moment;
}

export const defaultValue: Readonly<ITeacher> = {};
