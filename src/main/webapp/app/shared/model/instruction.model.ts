export interface IInstruction {
  id?: string;
  scheduleItemId?: number;
  videoURL?: string;
  photoURLs?: string;
  instruction?: string;
  title?: string;
}

export const defaultValue: Readonly<IInstruction> = {};
