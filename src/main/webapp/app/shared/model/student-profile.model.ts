import { Moment } from 'moment';

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE'
}

export interface IStudentProfile {
  id?: string;
  dream?: string;
  birthDate?: Moment;
  bloodGroup?: string;
  gender?: Gender;
  allergies?: string;
}

export const defaultValue: Readonly<IStudentProfile> = {};
