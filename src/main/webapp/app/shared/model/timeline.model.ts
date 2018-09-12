import { Moment } from 'moment';

export interface ITimeline {
  id?: string;
  date?: Moment;
  isVisible?: Moment;
}

export const defaultValue: Readonly<ITimeline> = {};
